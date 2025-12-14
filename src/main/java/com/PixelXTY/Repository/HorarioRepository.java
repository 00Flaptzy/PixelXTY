package com.PixelXTY.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PixelXTY.entity.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByUsuarioId(Long usuarioId);
    List<Horario> findByUsuarioIdAndDia(Long usuarioId, String dia);

}
