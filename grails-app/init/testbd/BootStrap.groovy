package testbd

class BootStrap {

    def init = { servletContext ->
      def adminRole = Role.findOrSaveWhere(authority : 'ROLE_ADMIN')
      def user = User.findOrSaveWhere(username : 'Admin', password : "sineti.1")
      if (!user.authorities.contains('adminRole')) {
        UserRole.create(user,adminRole,true)
      }
    }
    def destroy = {
    }
}
