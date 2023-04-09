package com.example.SistemaReservaDeTurnos.Repository;

import com.example.SistemaReservaDeTurnos.Entity.Turno;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public  class TurnoDaoH2 implements IDao<Turno>{

    Logger logger = Logger.getLogger(TurnoDaoH2.class);

    // Se crea una lista de turnos para almacenar todos los turnos yRetorna el turno agregado
    List<Turno> turnoList;


    // Agrega el turno a la lista de turnos
//    @Override
//    public Turno agregar(Turno turno) {
//        turnoList.add(turno);
//        return turno;
//    }


    PacienteDaoH2 pacienteDaoH2;
    OdontologoDaoH2 odontologoDaoH2;
@Autowired
    public TurnoDaoH2(List<Turno> turnoList, PacienteDaoH2 pacienteDaoH2, OdontologoDaoH2 odontologoDaoH2) {
        this.turnoList =new ArrayList<>();
        this.pacienteDaoH2 = pacienteDaoH2;
        this.odontologoDaoH2 = odontologoDaoH2;
    }



    // Agrega el turno a la lista de turnos
    @Override
    public Turno agregar(Turno turno) {
        turno.setId(turnoList.size()+1);
        turno.setPaciente(pacienteDaoH2.listar(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDaoH2.listar(turno.getOdontologo().getId()));
        turnoList.add(turno);
        return turno;
    }







    @Override
    public Turno listar(int id) {
        for(Turno turnoEnRevision:turnoList){
            if(turnoEnRevision.getId()==id){
                turnoEnRevision.setPaciente(pacienteDaoH2.listar(turnoEnRevision.getId()));
                turnoEnRevision.setOdontologo(odontologoDaoH2.listar(turnoEnRevision.getId()));
                return turnoEnRevision;
            }
        }
        return null;
    }


    // Inicializa la variable que almacenará el turno buscado
    // Recorre la lista de turnos
    // Si el turno actual tiene el id buscado
    // Se guarda el turno actual en la variable de turno buscado
    // Retorna el turno modificado


    public Optional<Turno> listarOptional(int id) {
        // Turno turnoBuscado=null;
        for(Turno turnoEnRevision:turnoList){
            if(turnoEnRevision.getId()==id){
                return Optional.of(turnoEnRevision);
            }
        }
        return Optional.empty();
    }


    // Busca el índice del turno en la lista de turnos
    // Reemplaza el turno antiguo con el turno nuevo en la lista de turnos
    // Retorna el turno modificado

    @Override
    public Turno modificar(Turno turno) {

        // int indice= turnos.indexOf(turno);
        // turnos.set(indice, turno);
        //   return buscar(turno.getId());

        //alternativa B
        eliminar(turno.getId());
        return agregar(turno);
    }


    // Busca el turno por el id
    // Si se encontró el turno, se elimina de la lista
    // Si no se encontró el turno, se envía un mensaje de error


    @Override
    public void eliminar(int id) {
        Turno buscado=listar(id);
        if (buscado!=null){
            turnoList.remove(buscado);
        }
        else{
           logger.info("Se a eliminado el turno "+ turnoList );
        }

    }


    // Retorna la lista completa de turnos

    @Override
    public List<Turno> listarTodos() {
        for (Turno turno : turnoList) {
            turno.setPaciente(pacienteDaoH2.listar(turno.getId()));
            turno.setOdontologo(odontologoDaoH2.listar(turno.getId()));
        }

        return turnoList;
    }


}
