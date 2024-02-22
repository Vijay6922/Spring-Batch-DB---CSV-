package com.example.DB_to_CSV.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.WritableResource;

import com.example.DB_to_CSV.Entity.Department;
import com.example.DB_to_CSV.processor.DepartmentItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Department> reader() {
		JdbcCursorItemReader<Department> cursorReader = new JdbcCursorItemReader<Department>();
		cursorReader.setDataSource(dataSource);
		cursorReader.setSql("Select departmentId,departmentName,managerId,managerName from Departments");
		cursorReader.setRowMapper(new DepartmentRowMapper());
		return cursorReader;
	}

	@Bean
	public DepartmentItemProcessor processor() {
		return new DepartmentItemProcessor();
	}

	@Bean
	public FlatFileItemWriter<Department> writer() {
		FlatFileItemWriter<Department> writer = new FlatFileItemWriter<Department>();
		writer.setResource((WritableResource) new ClassPathResource("department.csv"));

		DelimitedLineAggregator<Department> lineAggregator = new DelimitedLineAggregator<Department>();
		lineAggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<Department> fieldExtractor = new BeanWrapperFieldExtractor<Department>();
		fieldExtractor.setNames(new String[] { "departmentId", "departmentName", "managerId", "managerName" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(lineAggregator);
		return writer;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Department, Department>chunk(100).reader(reader())
				.processor(processor()).writer(writer()).build();

	}

	@Bean
	public Job DepartmentJob() {
		return jobBuilderFactory.get("job").flow(step1()).end().build();
	}
}
