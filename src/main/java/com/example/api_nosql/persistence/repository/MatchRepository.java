package com.example.api_nosql.persistence.repository;

import com.example.api_nosql.persistence.entity.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match, ObjectId> {

    Match findByIdChat(String idChat);

    @Query("{ $or: [{'uidEmployeeSeller' :  ?0 }, {'uidEmployeePurchaser' :  ?0 }] }")
    List<Match> findAllByEmployeeId(String sellerId);

    @Query("{ $or: [{'uidEmployeeSeller' :  ?0 }, {'uidEmployeePurchaser' :  ?0 }], 'status' : 'CONCLUIDO' }")
    List<Match> findAllByEmployeeIdCompleted(String sellerId);

    @Query("{ 'idIndustrySeller' :  ?0, 'status' :  { $nin :  ['CANCELADO', 'CONCLUIDO', 'ANDAMENTO', 'AGUARDANDO_PAGAMENTO'] }}")
    List<Match> findAllSolicitations(String idIndustrySeller);

    @Query(value = "{ 'idIndustryPurchaser' : ?0, 'idPost' : ?1, 'status' :  { $ne :  ?2 }}", exists = true)
    boolean existsMatch(String idIndustryPurchaser, Long idPost, String status);

}
