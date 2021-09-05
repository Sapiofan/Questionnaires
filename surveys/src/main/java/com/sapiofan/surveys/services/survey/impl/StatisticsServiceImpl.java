package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.RightAnswers;
import com.sapiofan.surveys.repository.survey.RightAnswersRepository;
import com.sapiofan.surveys.services.survey.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private RightAnswersRepository rightAnswersRepository;

    @Transactional
    public List<RightAnswers> results(UUID id) {
        return rightAnswersRepository.findAllResultsBySurvey(id);
    }

    @Transactional
    public void saveResult(RightAnswers answers) {
        rightAnswersRepository.save(answers);
    }
}
