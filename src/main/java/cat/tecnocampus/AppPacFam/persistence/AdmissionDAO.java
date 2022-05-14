package cat.tecnocampus.AppPacFam.persistence;

import java.util.Date;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.AdmissionDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;
import cat.tecnocampus.AppPacFam.domain.Admission;
import cat.tecnocampus.AppPacFam.domain.Admission.HospitalCareType;

@Repository
public class AdmissionDAO implements cat.tecnocampus.AppPacFam.application.AdmissionDAO {

	private JdbcTemplate jdbcTemplate;

	public AdmissionDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<AdmissionDTO> admissionRowMapperLazy = (resultSet, i) -> {

		AdmissionDTO admission = new AdmissionDTO();

		admission.setAdmissionId(resultSet.getString("admissionId"));
		if (resultSet.getDate("finalDate") != null)
			admission.setFinalDate(resultSet.getDate("finalDate"));
		admission.setStartDate(resultSet.getDate("startDate"));
		admission.setHospitalCareType(Admission.HospitalCareType.valueOf(resultSet.getString("hospitalCareType")));

		return admission;
	};

	ResultSetExtractorImpl<AdmissionDTO> admissionsRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("admissionId").newResultSetExtractor(AdmissionDTO.class);

	RowMapperImpl<AdmissionDTO> admissionRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("admissionId")
			.newRowMapper(AdmissionDTO.class);

	@Override
	public List<AdmissionDTO> getAdmissionsByPatientId(String patientId) {
		final var query = "select admissionId, hospitalCareType, startDate, finalDate from admission where patientId = ?";

		return jdbcTemplate.query(query, admissionRowMapperLazy, patientId);
	}

	@Override
	public AdmissionDTO getActiveAdmissionByPatientId(String patientId) {
		final var query = "select * from admission where patientId = ? AND finalDate IS NULL";
		try {
			return jdbcTemplate.queryForObject(query, admissionRowMapperLazy, patientId);
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(patientId);
		}
	}

	@Override
	public void setNewAdmission(AdmissionDTO admission, String patientId) {
		final var query = "INSERT INTO admission (admissionId, hospitalCareType, startDate, patientId) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(query, admission.getAdmissionId(), admission.getHospitalCareType().toString(), new Date(),
				patientId);
	}

	@Override
	public void modifyAdmission(AdmissionDTO admission) {
		final var query = "UPDATE admission SET startDate = ?, finalDate = ?, hospitalCareType = ?  WHERE admissionId = ?";
		jdbcTemplate.update(query, admission.getStartDate(), admission.getFinalDate(),
				admission.getHospitalCareType().toString(), admission.getAdmissionId());
	}

	@Override
	public void modifyAdmissionTypeByPatientId(String patientId) {
		final var queryGet = "select * from admission where patientId = ? AND finalDate IS NULL";
		AdmissionDTO admission = jdbcTemplate.queryForObject(queryGet, admissionRowMapperLazy, patientId);

		final var queryUpdate = "UPDATE admission SET finalDate = ?, hospitalCareType = ?  WHERE patientId = ?";
		jdbcTemplate.update(queryUpdate, new Date(), admission.getHospitalCareType().toString(), patientId);

		String newHospitalCareType;
		if (admission.getHospitalCareType() == HospitalCareType.quirurgic)
			newHospitalCareType = HospitalCareType.urgencies.toString();
		else
			newHospitalCareType = HospitalCareType.quirurgic.toString();

		AdmissionDTO newAdmission = new AdmissionDTO();
		final var queryInsert = "INSERT INTO admission (admissionId, hospitalCareType, startDate, patientId) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(queryInsert, newAdmission.getAdmissionId(), newHospitalCareType, new Date(), patientId);

	}

}
