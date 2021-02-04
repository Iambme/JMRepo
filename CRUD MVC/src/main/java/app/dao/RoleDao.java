package app.dao;

import app.model.Role;

public interface RoleDao {
void saveRole(Role role);
Role getRoleById(int id);
void deleteRole(int id);
}
