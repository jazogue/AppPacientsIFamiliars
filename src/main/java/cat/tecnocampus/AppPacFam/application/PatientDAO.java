package cat.tecnocampus.AppPacFam.application;


import java.time.LocalDate;
import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;

public interface PatientDAO {

    PatientDTO getProfileLazy(String id);
    List<ProfileDTO> getProfilesLazy();

    ProfileDTO getProfile(String id);
    List<ProfileDTO> getProfiles();

    ProfileDTO addProfile(ProfileDTO profile);

    void saveLikes(String origin, List<Like> likes);

    void updateLikeToMatch(String id, String id1, LocalDate matchDate);

    ProfileDTO getProfileByName(String name);

    ProfileDTO getProfileByNameLazy(String name);
    
    
	PatientDTO getPatientById(String id);
}
