package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.dao.SurveysManagementDao;
import com.sapiofan.surveys.services.SurveysManagementService;
import org.springframework.beans.factory.annotation.Autowired;

public class SurveysManagementServiceImpl implements SurveysManagementService {
    @Autowired
    private SurveysManagementDao surveysManagementDao;

    public SurveysManagementServiceImpl(SurveysManagementDao surveysManagementDao) {
        this.surveysManagementDao = surveysManagementDao;
    }

    @Override
    public void createSurvey() {
        surveysManagementDao.createSurvey();
    }

    @Override
    public void updateSurvey() {
        surveysManagementDao.updateSurvey();
    }

    @Override
    public void deleteSurvey() {
        surveysManagementDao.deleteSurvey();
    }
}
