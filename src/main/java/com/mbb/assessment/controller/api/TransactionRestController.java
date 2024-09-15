package com.mbb.assessment.controller.api;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.mbb.assessment.dto.TransactionDTO;
import com.mbb.assessment.dto.error.ApiErrorResponse;
import com.mbb.assessment.exception.TransactionNotFoundException;
import com.mbb.assessment.service.command.TransactionCommandService;
import com.mbb.assessment.service.query.TransactionQueryService;
import com.mbb.assessment.service.query.criteria.TransactionCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TransactionRestController {

    private final TransactionQueryService transactionQueryService;

    private final TransactionCommandService transactionCommandService;

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(TransactionNotFoundException ex) {
        ApiErrorResponse apiErrRes = ApiErrorResponse.builder()
            .errorCode(HttpStatus.NOT_FOUND)
            .errorMessage(ex.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrRes);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(TransactionCriteria criteria, Pageable pageable) {
        log.info("getAllTransactions - criteria : {} | pageable : {}", criteria, pageable);

        Page<TransactionDTO> result = transactionQueryService.findAllByCriteria(criteria, pageable,
            NamedEntityGraph.loading("transactionCustomer"));

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
            ServletUriComponentsBuilder.fromCurrentRequest(), result);

        log.info("getAllTransactions - result : {}", result);

        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }

    @PutMapping("/transaction")
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO req) throws TransactionNotFoundException {
        log.info("updateTransaction - request : {}", req);

        TransactionDTO result = transactionCommandService.update(req);

        log.info("updateTransaction - result : {}", result);
        return ResponseEntity.ok().body(result);
    }
}
