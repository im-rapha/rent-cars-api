package br.gov.sp.fatec.service.impl;

import br.gov.sp.fatec.domain.entity.Carro;
import br.gov.sp.fatec.domain.enums.CarroStatus;
import br.gov.sp.fatec.domain.mapper.CarroMapper;
import br.gov.sp.fatec.domain.request.CarroRequest;
import br.gov.sp.fatec.domain.request.CarroUpdateRequest;
import br.gov.sp.fatec.domain.response.CarroResponse;
import br.gov.sp.fatec.repository.CarroRepository;
import br.gov.sp.fatec.service.CarroService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarroServiceImpl implements CarroService {
    private final CarroRepository carroRepository;
    private final CarroMapper carroMapper;

    @Override
    public CarroResponse save(CarroRequest carroRequest) {
        Carro carroSaved = carroRepository.save(carroMapper.map(carroRequest));
        // Por padrão, cria com o status disponível
        carroSaved.setStatus(CarroStatus.DISPONIVEL);
        
        return carroMapper.map(carroSaved);
    }

    @Override
    public CarroResponse findById(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        return new CarroResponse(carro.getAno(), carro.getMarca(), carro.getModelo());
    }

    @Override
    public List<CarroResponse> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream().map(carroMapper::map).toList();
    }

    @Override
    public void updateById(Long id, CarroUpdateRequest carroUpdateRequest) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carro não encontrado"));
        carro.setAno(carroUpdateRequest.ano());
        carro.setMarca(carroUpdateRequest.marca());
        carro.setModelo(carroUpdateRequest.modelo());
        carroRepository.save(carro);
    }

    @Override
    public void deleteById(Long id) {
        carroRepository.deleteById(id);
    }
}
