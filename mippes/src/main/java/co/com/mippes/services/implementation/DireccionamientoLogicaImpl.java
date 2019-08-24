package co.com.mippes.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mippes.dao.DireccionamientoDAO;
import co.com.mippes.dto.DireccionamientoDTO;
import co.com.mippes.entity.Direccionamiento;
import co.com.mippes.services.ConsultaMipres;
import co.com.mippes.services.DireccionamientoLogica;

@Service
public class DireccionamientoLogicaImpl implements DireccionamientoLogica{

	@Autowired
	private ConsultaMipres consultaMipres;
	
	@Autowired
	private DireccionamientoDAO direccionamientoDAO;
	
	@Override
	public void procesaDireccionamientos() {
		
		List<DireccionamientoDTO> direccionamientos = consultaMipres.consultaDireccionamiento("", "");
		
		direccionamientos.forEach(direccio -> {
			ModelMapper modelMapper = new ModelMapper();
			Direccionamiento direccionamiento = modelMapper.map(direccio, Direccionamiento.class);
			direccionamientoDAO.save(direccionamiento);
		});
		
		
		
	}

}
