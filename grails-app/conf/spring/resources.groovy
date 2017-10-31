import testbd.auth.UserPasswordEncoderListener
import testbd.auth.UserPasswordEncoderListener
import testbd.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
}
