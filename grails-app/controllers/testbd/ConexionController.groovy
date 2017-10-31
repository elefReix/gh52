package testbd

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ConexionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Conexion.list(params), model:[conexionCount: Conexion.count()]
    }

    def show(Conexion conexion) {
        respond conexion
    }

    def create() {
        respond new Conexion(params)
    }

    @Transactional
    def save(Conexion conexion) {
        if (conexion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (conexion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond conexion.errors, view:'create'
            return
        }

        conexion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'conexion.label', default: 'Conexion'), conexion.id])
                redirect conexion
            }
            '*' { respond conexion, [status: CREATED] }
        }
    }

    def edit(Conexion conexion) {
        respond conexion
    }

    @Transactional
    def update(Conexion conexion) {
        if (conexion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (conexion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond conexion.errors, view:'edit'
            return
        }

        conexion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'conexion.label', default: 'Conexion'), conexion.id])
                redirect conexion
            }
            '*'{ respond conexion, [status: OK] }
        }
    }

    @Transactional
    def delete(Conexion conexion) {

        if (conexion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        conexion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'conexion.label', default: 'Conexion'), conexion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'conexion.label', default: 'Conexion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
