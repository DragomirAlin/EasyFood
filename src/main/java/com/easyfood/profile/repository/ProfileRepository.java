package com.easyfood.profile.repository;

import com.easyfood.profile.persistence.ProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileUser, Long> {

//    ProfileUser saveProfile(ProfileUser profileUser);
}
