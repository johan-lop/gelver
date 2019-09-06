package co.com.mippes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.mippes.entity.Direccionamiento;
import co.com.mippes.entity.Programacion;

public interface ProgramacionDAO extends JpaRepository<Programacion, Integer>{

	
}
