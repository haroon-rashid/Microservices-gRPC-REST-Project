/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paris.agreement.repository;



import com.paris.agreement.model.TextAnalysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextAnalysisRepository extends CrudRepository<TextAnalysis, String> {

}
