package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndData(Long medicoId, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime dataAfter, LocalDateTime dataBefore);
}
