package edu.alenkin.busyman.service;

import edu.alenkin.busyman.model.Task;
import edu.alenkin.busyman.repository.TaskRepository;
import edu.alenkin.busyman.testData.TaskTestData;
import edu.alenkin.busyman.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static edu.alenkin.busyman.testData.CategoryTestData.family;
import static edu.alenkin.busyman.testData.CategoryTestData.getUpdatedCategory;
import static edu.alenkin.busyman.testData.TaskTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class JpaPriorityServiceTest extends AbstractServiceTest {

}
