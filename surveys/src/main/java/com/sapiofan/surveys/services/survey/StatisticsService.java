package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.RightAnswers;

import java.util.List;
import java.util.UUID;

public interface StatisticsService {
    List<RightAnswers> results(UUID id);

    void saveResult(RightAnswers answers);
}
