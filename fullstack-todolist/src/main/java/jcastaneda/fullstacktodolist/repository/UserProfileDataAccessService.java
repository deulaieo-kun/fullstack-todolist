package jcastaneda.fullstacktodolist.repository;

import jcastaneda.fullstacktodolist.datastore.FakeUserProfileDataStore;
import jcastaneda.fullstacktodolist.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    public List<UserProfile> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
