package com.mbb.assessment.config;

import com.mbb.assessment.batch.listener.JobCompletionNotificationListener;
import com.mbb.assessment.batch.processor.TransactionItemProcessor;
import com.mbb.assessment.batch.record.TransactionRecord;
import com.mbb.assessment.dto.TransactionDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<TransactionRecord> reader() {
        return new FlatFileItemReaderBuilder<TransactionRecord>()
            .name("transactionItemReader")
            .resource(new ClassPathResource("dataSource.txt"))
            .linesToSkip(1)
            .delimited()
            .delimiter("|")
            .names("accountNo", "trxAmount", "description", "trxDate", "trxTime", "customerId")
            .targetType(TransactionRecord.class)
            .build();
    }

    @Bean
    public TransactionItemProcessor processor() {
        return new TransactionItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<TransactionDTO> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TransactionDTO>()
            .sql("INSERT INTO mbb_transaction (trx_reference_no, account_no, trx_amount, description, trx_date, trx_time, customer_id, created_at, created_by) VALUES (:trxRefNo, :accountNo, :trxAmount, :description, :trxDate, :trxTime, :customerId, current_timestamp, 'system')")
            .dataSource(dataSource)
            .beanMapped()
            .build();
    }

    @Bean
    public Job importTransactionJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importTransactionJob", jobRepository)
            .listener(listener)
            .start(step1)
            .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
        FlatFileItemReader<TransactionRecord> reader, TransactionItemProcessor processor, JdbcBatchItemWriter<TransactionDTO> writer) {
        return new StepBuilder("step1", jobRepository)
            .<TransactionRecord, TransactionDTO> chunk(3, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
