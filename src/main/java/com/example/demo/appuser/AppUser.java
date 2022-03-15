package com.example.demo.appuser;

import com.example.demo.chat.Chat;
import com.example.demo.friend.Friend;
import com.example.demo.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

/**
 * AppUser Class represents an instance of an application user and an entity in the database.
 *
 * Extends the UserDetails Interface and contains user detail fields such as ID, first name, last name, email, password,
 * appUserRoles, locked, enabled, and relevant getter and setter methods.
 *
 * @author jakub
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AppUser implements UserDetails {

    /**
     * Unique ID for each user that is incrementally generated.
     */
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    /**
     * User's first name string.
     */
    private String firstName;

    /**
     * User's last name string.
     */
    private String lastName;

    /**
     * The email string of the user. Unique identifier also used as the username.
     */
    private String email;

    /**
     * The password string of the user.
     */
    private String password;

    /**
     * String containing the path to the user's profile picture.
     */
    private String profilePicture;

    /**
     * String containing the user's personal profile bio.
     */
    private String profileBio;

    /**
     * String containing the user's academic course.
     */
    private String academicCourse;

    @OneToMany(mappedBy = "participantOne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats1;

    @OneToMany(mappedBy = "participantTwo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats2;

    @OneToMany(mappedBy = "postUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends1;

    @OneToMany(mappedBy = "usersFriend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends2;

    /**
     * The ENUM role of the user.
     */
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    /**
     * True if a user's account has been locked, or false if it's unlocked.
     */
    private Boolean locked = false;

    /**
     * True if a user's account is enabled, false if it has been disabled.
     */
    private Boolean enabled = true;

    /**
     * An Instance of an AppUser.
     * @param firstName First name string.
     * @param lastName Last name string.
     * @param email Email string.
     * @param password Password string.
     * @param appUserRole Roles in an appUserRole instance.
     */
    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    /**
     * Function for getting a user's granted authorities.
     * @return Returns a list of authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

    public void setFirstName(String firstName){this.firstName = firstName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public void setEmail(String email){this.email = email;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
