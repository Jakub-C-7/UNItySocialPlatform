package com.example.demo.groupMember;

import com.example.demo.appuser.AppUser;
import com.example.demo.group.AppGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * GroupMemberRepository Interface performs GroupMember queries between the application and the database.
 *
 * Creating, deleting, editing group membership records.
 *
 * @author jakub
 */
@Repository
@Transactional(readOnly = true)
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    GroupMember findByGroupAndUser(AppGroup group, AppUser user);

}
