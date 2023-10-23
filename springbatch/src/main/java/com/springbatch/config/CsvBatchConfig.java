package com.springbatch.config;

import com.springbatch.entity.Employee;
import com.springbatch.processor.EmployeeProcessor;
import com.springbatch.repository.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    //TODO Create Reader

    @Bean
    public FlatFileItemReader<Employee> employeeReader(){
        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();

        itemReader.setResource(new FileSystemResource("src/main/resources/Employee-data.csv"));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper=new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","fullName","jobTitle","department","businessUnit","gender");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    //TODO Create Processors

    @Bean
    public EmployeeProcessor employeeProcessor(){
        return new EmployeeProcessor();
    }

    //TODO Create writer

    @Bean
    public RepositoryItemWriter<Employee> employeeItemWriter(){
        RepositoryItemWriter<Employee> itemWriter=new RepositoryItemWriter<>();

        itemWriter.setRepository(employeeRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    //TODO Create Step

    @Bean
    public Step step(){
        return stepBuilderFactory
                .get("step-1").<Employee,Employee>chunk(10)
                .reader(employeeReader())
                .processor(employeeProcessor())
                .writer(employeeItemWriter())
                .build();
    }


    //TODO Create Job

    @Bean
    public Job job(){
        return jobBuilderFactory.get("employees-job")
                .flow(step())
                .end().build();
    }
}
