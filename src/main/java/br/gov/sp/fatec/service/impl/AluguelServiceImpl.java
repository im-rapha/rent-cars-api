package br.gov.sp.fatec.service.impl;

import br.gov.sp.fatec.domain.entity.Aluguel;
import br.gov.sp.fatec.domain.mapper.AluguelMapper;
import br.gov.sp.fatec.domain.request.AluguelRequest;
import br.gov.sp.fatec.domain.request.AluguelUpdateRequest;
import br.gov.sp.fatec.domain.response.AluguelResponse;
import br.gov.sp.fatec.repository.AluguelRepository;
import br.gov.sp.fatec.service.AluguelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AluguelServiceImpl implements AluguelService {

    private final AluguelRepository aluguelRepository;
    private final AluguelMapper aluguelMapper;

    @Override
    public AluguelResponse save(AluguelRequest aluguelRequest) {
        Aluguel aluguelSaved = aluguelRepository.save(aluguelMapper.map(aluguelRequest));       
        return aluguelMapper.map(aluguelSaved);
    }

    @Override
    public AluguelResponse findById(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        return new AluguelResponse(aluguel.getValor());
    }

    @Override
    public List<AluguelResponse> findAll() {
        List<Aluguel> alugueis = aluguelRepository.findAll();
        return alugueis.stream().map(aluguelMapper::map).toList();
    }

    @Override
    public void updateById(Long id, AluguelUpdateRequest aluguelUpdateRequest) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        aluguel.setValor(aluguelUpdateRequest.valor());
        aluguelRepository.save(aluguel);
    }

    @Override
    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }
}
