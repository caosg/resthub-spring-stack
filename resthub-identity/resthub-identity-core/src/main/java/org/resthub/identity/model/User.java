package org.resthub.identity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Describe a user account.<br/>
 * A User has some attributes such as login, password, email, ... TODO there is
 * * some fields which have in comments "Nullable=false" , this cannot be remove
 * from comments without refactoring the abstractTestDao class
 */
@Entity
@Table
@XmlRootElement
public class User extends Identity {

	private static final long serialVersionUID = -7139715798005612136L;
	/**
	 * List of attributes for a user
	 * */
	protected String firstName = null;
	protected String lastName = null;
	protected String login = null;
	protected String password = null;
	protected String email = null;

	/**
	 * List of groups in which the user is
	 * */
	protected List<Group> groups = null;

	/**
	 * default Constructor
	 * */
	public User() {
	}

	/**
	 * getLogin
	 * 
	 * @return the user login
	 * */
	@Column(unique = true/* , nullable = false */)
	public String getLogin() {
		return login;
	}

	/**
	 * setLogin
	 * 
	 * @param login
	 *            the login to be set for the user
	 * */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * gets the Password<br/>
	 * The password can not be given in the XML/JSON representation of the user
	 * 
	 * @return user's password
	 * */
	@Column(/* nullable = false */)
	@XmlTransient
	public String getPassword() {
		return password;
	}

	/**
	 * sets the user's Password
	 * 
	 * @param password
	 *            ,the password to be set to the user
	 * */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * gets the user's FirstName
	 * 
	 * @return user's FirstName
	 * */
	@Column
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets the user's FirstName
	 * 
	 * @param firstName
	 *            the firstName to be set
	 * */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get's the user's lastName
	 * 
	 * @return user's lastName
	 * */
	@Column
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets the user's LastName
	 * 
	 * @param lastName
	 *            the lastName to be set
	 * */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * gets the user's Email
	 * 
	 *@return user's email;
	 * */
	@Column(/* nullable = false */)
	public String getEmail() {
		return email;
	}

	/**
	 * sets the user's email
	 * 
	 * @param email
	 *            the email to be set
	 * */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * gets the user's Groups
	 * 
	 * @return the list of groups in which the user is. The List could be null
	 *         is the user is in no group
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "user_group")
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * sets the Groups in which the user is
	 * 
	 * @param groups
	 *            the list of groups in which the user belongs
	 * */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	/**
	 * Adds a {@link User} in a group.
	 * 
	 * @param group
	 *            the group to which the user should be added the insertion has
	 *            to be done in both way in order to prevent trouble linked to
	 *            cache
	 * 
	 */
	public void addToGroup(Group group) {
		if (groups == null) {
			this.groups = new ArrayList<Group>();
		}
		this.groups.add(group);
	}

	/**
	 * removes the user form a group
	 * 
	 * @param group
	 *            the group from which the user should be remove
	 * */
	public void removeFromGroup(Group group) {
		if (groups != null) {
			groups.remove(group);
		}
	}

	/**
	 * returns a {@link String} representation of the user. Display the ID,
	 * Login and email of the user
	 * */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User[");
		sb.append("Id: ").append(this.getId()).append(", ");
		sb.append("Login: ").append(this.getLogin()).append(", ");
		sb.append("Email: ").append(this.getEmail());
		sb.append("]");
		return sb.toString();
	}
}
