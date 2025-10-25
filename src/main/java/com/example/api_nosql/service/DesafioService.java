package com.example.api_nosql.service;

import com.example.api_nosql.api.challenges.input.DesafioRequest;
import com.example.api_nosql.api.challenges.input.SolucaoRequest;
import com.example.api_nosql.api.challenges.output.DesafioResponse;
import com.example.api_nosql.config.redis.RedisMessagePublisher;
import com.example.api_nosql.mapper.DesafioMapper;
import com.example.api_nosql.persistence.entity.Desafio;
import com.example.api_nosql.persistence.repository.DesafioRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DesafioService {

    private final DesafioRepository repository;
    private final RedisMessagePublisher redisMessagePublisher;

    public List<DesafioResponse> findByEmployeeId(final Long id) {
        List<Desafio> list = repository.findByIdEmployeeQuestion(id);
        return list.stream().map(DesafioService::fromDesafio).collect(Collectors.toList());
    }

    public DesafioResponse create(final DesafioRequest request) {
        Desafio desafio = toDesafio(request);
        return fromDesafio(repository.save(desafio));
    }

    public DesafioResponse createSolucao(final SolucaoRequest solucaoRequest, final String idDesafio) {
        Desafio desafio = repository.findById(new ObjectId(idDesafio)).orElseThrow(() -> new RuntimeException("Desafio not found"));
        desafio.getSolutions().addLast(toSolucao(solucaoRequest));
        redisMessagePublisher.publish("desafio." + idDesafio, toSolucao(solucaoRequest));
        return fromDesafio(repository.save(desafio));
    }

    private static DesafioResponse fromDesafio(final Desafio desafio){
        return DesafioMapper.toResponse(desafio);
    }

    private static Desafio toDesafio(final DesafioRequest  request){
        return DesafioMapper.toEntity(request);
    }

    private static Desafio.Solucao toSolucao(final SolucaoRequest request){
        return DesafioMapper.toSolucao(request);
    }

}
