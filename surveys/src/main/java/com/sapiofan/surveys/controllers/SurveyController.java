package com.sapiofan.surveys.controllers;


import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.services.impl.SurveyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class SurveyController {
    @Autowired
    public SurveyServiceImpl surveyService;

    Logger logger = LoggerFactory.getLogger(SurveyController.class);

    @PostMapping(value = "/createSurvey")
    public String addSurvey(@RequestParam("name") String name,
                            @RequestParam("surveyId") Long surveyId,
                            Model model) {
        Survey survey;
        if (surveyId == 0) {
            survey = new Survey();
            survey.setName(name);
            survey.setSize(0);
            survey.setUser(surveyService.findUserById(1l));
        } else {
            survey = surveyService.findSurveyById(surveyId);
            survey.setName(name);
        }
        surveyService.save(survey);
        model.addAttribute("questions", surveyService.findAllQuestions(survey.getId()));
        model.addAttribute("surveyId", survey.getId());
        model.addAttribute("questionId", 0);
        return "listOfQuestions";
    }

    @PostMapping(value = "/addQuestion", params = "returnToQuestions")
    public String returnToQuestions(Model model,
                                    @RequestParam("surveyId") Long surveyId) {
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", 0);
        return "listOfQuestions";
    }

    @GetMapping(value = "/addQuestion", params = "add")
    public String addQuestionForm(Model model,
                                  @RequestParam("surveyId") Long surveyId,
                                  @RequestParam("questionId") Long questionId) {
        List<Answer> answers = new ArrayList<>();
        model.addAttribute("answers", answers);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", questionId);
        return "addQuestion";
    }

    @PostMapping(value = "/addQuestion", params = "add")
    public String addQuestion(@RequestParam("question") String inputtedQuestion,
                              @RequestParam(name = "surveyId") Long surveyId,
                              @RequestParam(name = "questionId") Long questionId,
                              Model model) {
        Question question;
        if (questionId == 0) {
            Survey survey = surveyService.findSurveyById(surveyId);
            question = new Question(survey.getQuestions().size() + 1, inputtedQuestion, survey);
        } else {
            question = surveyService.findQuestionById(questionId);
            question.setDescription(inputtedQuestion);
        }
        surveyService.saveQuestion(question);
        model.addAttribute("questionId", question.getId());
        model.addAttribute("question", question.getDescription());
        return "listOfAnswers";
    }

    @GetMapping(value = "/addQuestion", params = "changeSurveyName")
    public String changeSurveyName(@RequestParam(name = "surveyId") Long surveyId, Model model) {
        model.addAttribute("surveyId", surveyId);
        return "survey";
    }

    @GetMapping(value = "/addAnswer", params = "addAnswer")
    public String listOfAnswers(Model model,
                                @RequestParam("questionId") String questionId) {
        model.addAttribute("questionId", questionId);
        return "addAnswer";
    }

    @GetMapping(value = "/addAnswer", params = "changeQuestionName")
    public String changeQuestionName(Model model,
                                     @RequestParam("questionId") Long questionId) {
        model.addAttribute("surveyId", surveyService.findQuestionById(questionId).getSurvey().getId());
        model.addAttribute("questionId", questionId);
        return "addQuestion";
    }

    @GetMapping("/listOfAnswers")
    public String showListAnswers(@RequestParam("questionId") Long questionId, Model model) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyService.findQuestionById(questionId).getDescription());
        model.addAttribute("answers", surveyService.findAllAnswers(questionId));
        return "listOfAnswers";
    }

    @GetMapping("/listOfAnswers/{number}")
    public String showAnswers(@PathVariable("number") Integer number,
                              @RequestParam("surveyId") Long surveyId,
                              Model model) {
        Long questionId = surveyService.findSurveyById(surveyId).getQuestions().get(number - 1).getId();
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyService.findQuestionById(questionId).getDescription());
        model.addAttribute("answers", surveyService.findAllAnswers(questionId));
        return "listOfAnswers";
    }


    @PostMapping(value = "/listOfAnswers", params = "saveAnswer")
    public String addAnswer(Model model,
                            @RequestParam("questionId") Long questionId,
                            @RequestParam("answer") String inputtedAnswer,
                            @RequestParam("correctAnswer") String correctness) {
        Question question = surveyService.findQuestionById(questionId);
        List<Answer> answers = surveyService.findAllAnswers(questionId);
        boolean flag = false;
        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).getAnswer().equals(inputtedAnswer)) {
                flag = true;
                break;
            }
        }
        if(!flag){
            Answer answer = new Answer(question.getAnswers().size() + 1, question, inputtedAnswer, correctness.equals("1"));
            surveyService.saveQuestion(question);
            surveyService.saveAnswer(answer);
        }
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", surveyService.findAllAnswers(questionId));
        return "listOfAnswers";
    }


    @GetMapping("/answer/{number}")
    public String getAnswerByNumber(@PathVariable("number") Integer number,
                                    @RequestParam("questionId") Long questionId,
                                    Model model) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("answer", surveyService.findQuestionById(questionId).getAnswers().get(number - 1));
        return "showAnswer";
    }

    @GetMapping(("/deleteAnswer/{number}"))
    public String deleteAnswerById(@PathVariable("number") Integer number,
                                   @RequestParam("questionId") Long questionId,
                                   Model model) {
        Question question = surveyService.findQuestionById(questionId);
        Answer answer = question.getAnswers().get(number - 1);
        for (int i = 0; i < surveyService.findAllAnswers(questionId).size() - answer.getNumber(); i++) {
            Answer answer1 = question.getAnswers().get(number);
            answer1.setNumber(answer1.getNumber() - 1);
            surveyService.saveAnswer(answer1);
        }
        surveyService.deleteAnswerById(answer.getId());
        surveyService.saveQuestion(question);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", surveyService.findAllAnswers(questionId));
        return "listOfAnswers";
    }

    @GetMapping(("/editAnswer/{number}"))
    public String editAnswer(@PathVariable("number") Integer number,
                             @RequestParam("questionId") Long questionId,
                             Model model) {
        Answer answer = surveyService.findQuestionById(questionId).getAnswers().get(number - 1);
        model.addAttribute("answerId", answer.getId());
        model.addAttribute("questionId", questionId);
        model.addAttribute("answer", answer);
        return "updateAnswer";
    }

    @PostMapping(value = "/updateAnswer", params = "saveAnswer") //link on row is wrong
    public String updateAnswer(Model model,
                               @RequestParam("questionId") Long questionId,
                               @RequestParam("answerId") Long answerId,
                               @RequestParam("number") Integer number,
                               @RequestParam("answer") String inputtedAnswer,
                               @RequestParam("correctAnswer") String correctness) {
        Question question = surveyService.findQuestionById(questionId);
        Answer answer = surveyService.findAnswerById(answerId);
        answer.setAnswer(inputtedAnswer);
        answer.setCorrectness(correctness.equals("1"));
        if (number <= 0 || number > surveyService.findAllAnswers(questionId).size()) {
        } else if (number.equals(answer.getNumber())) {
        } else {
            if (number > answer.getNumber()) {
                for (int i = 1; i < number - answer.getNumber() + 1; i++) {
                    Answer answer1 = surveyService.findQuestionById(questionId).getAnswers().get(answer.getNumber() + i - 1);
                    answer1.setNumber(answer1.getNumber() - 1);
                    surveyService.saveAnswer(answer1);
                }
            } else {
                for (int i = 0; i < answer.getNumber() - number; i++) {
                    Answer answer1 = surveyService.findQuestionById(questionId).getAnswers().get(number + i - 1);
                    answer1.setNumber(answer1.getNumber() + 1);
                    surveyService.saveAnswer(answer1);
                }
            }
            answer.setNumber(number);
        }
        surveyService.saveAnswer(answer);
        surveyService.saveQuestion(question);
        List<Answer> answers = surveyService.findAllAnswers(questionId)
                .stream()
                .sorted(Comparator.comparingInt(Answer::getNumber))
                .collect(Collectors.toList());
        for (Answer answer1 : answers) {
            logger.info(""+answer1.getAnswer());
        }
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", answers);
        return "listOfAnswers";
    }

    @PostMapping(value = "/updateAnswer", params = "backToAnswersList")
    public String backToAnswersList(@RequestParam("questionId") Long questionId,
                                    Model model){
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyService.findQuestionById(questionId).getDescription());
        model.addAttribute("answers", surveyService.findAllAnswers(questionId));
        return "listOfAnswers";
    }

    @GetMapping("/question/{number}")
    public String getQuestionByNumber(@PathVariable("number") Integer number,
                                      @RequestParam("questionId") Long questionId,
                                      Model model) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("answer", surveyService.findQuestionById(questionId).getAnswers().get(number - 1));
        return "listOfAnswers";
    }

    @GetMapping(value = "/addAnswer", params = "addQuestion")
    public String addQuestionWithAnswers(Model model,
                                         @RequestParam("questionId") Long questionId) {
        Question question = surveyService.findQuestionById(questionId);
        model.addAttribute("questionId", 0);
        model.addAttribute("surveyId", question.getSurvey().getId());
        List<Question> questions = surveyService.findAllQuestions(question.getSurvey().getId());
//        questions.stream().sorted(Comparator.comparingInt(Question::getNumber));
        model.addAttribute("questions", questions);
        return "listOfQuestions";
    }

    @GetMapping(value = "/addQuestion", params = "saveSurvey")
    public String saveSurvey(Model model, @RequestParam("surveyId") Long surveyId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        survey.setSize(survey.getQuestions().size());
        surveyService.save(survey);
        model.addAttribute("surveys", surveyService.findAllSurveys());
        return "list";
    }

    @GetMapping(value = "/addQuestion", params = "deleteSurvey")
    public String deleteSurvey(Model model, @RequestParam("surveyId") Long surveyId){
        surveyService.deleteSurveyById(surveyId);
        return "main";
    }

    @GetMapping(("/deleteQuestion/{number}"))
    public String deleteQuestionById(@PathVariable("number") Integer number,
                                   @RequestParam("surveyId") Long surveyId,
                                   Model model) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Question question = survey.getQuestions().get(number-1);
        for (int i = 0; i < surveyService.findAllQuestions(surveyId).size() - question.getNumber(); i++) {
            Question question1 = survey.getQuestions().get(number);
            question1.setNumber(question1.getNumber() - 1);
            surveyService.saveQuestion(question1);
        }
        surveyService.deleteQuestionById(question.getId());
        surveyService.save(survey);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", 0);
        model.addAttribute("questions", surveyService.findAllQuestions(surveyId));
        return "listOfQuestions";
    }

    @GetMapping("/survey/{id}")
    public String startPageSurvey(@PathVariable("id") Long id, Model model) {
        model.addAttribute("surveyId", id);
        List<Question> questions = surveyService.findAllQuestions(id).stream().sorted(Comparator.comparingInt(Question::getNumber)).collect(Collectors.toList());
        Survey survey = surveyService.findSurveyById(id);
        survey.setQuestions(questions);
        surveyService.save(survey);
        model.addAttribute("question", questions.get(0));
        logger.info(""+questions.get(0).getNumber());
        return "startSurvey";
    }

    @PostMapping(value = "/survey/{id}/{number}", params = "start")
    public String startSurvey(@PathVariable("id") Long id, @PathVariable("number") Integer number, Model model) {
        model.addAttribute("surveyId", id);
        Survey survey = surveyService.findSurveyById(id);
        List<Answer> answers = survey.getQuestions().get(number-1).getAnswers();
//        for (Answer answer : answers) {
//            logger.info(answer.getAnswer());
//        }
        model.addAttribute("question", survey.getQuestions().get(number-1));
//        logger.info(survey.getQuestions().get(0).getDescription());
        model.addAttribute("questions", survey.getQuestions().size());
        model.addAttribute("answers", answers);
        SurveyResults results = new SurveyResults();
        results.setSurvey(survey);
        results.setUser(surveyService.findUserById(1l));
        Timestamp ts = Timestamp.from(Instant.now());
        results.setStart(ts);
        results.setEnd_time(ts);
        surveyService.saveSurveyResults(results);
        model.addAttribute("resultId", results.getId());
        return "passSurvey";
    }

    @GetMapping("/survey/{id}/{number}")
    public String getSurvey(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                            @RequestParam("resultId") UUID resultId,
                            @RequestParam("radio") String answer,
                            Model model) {
        Survey survey = surveyService.findSurveyById(id);
        SurveyResults results = surveyService.findSurveyResultsById(resultId);
        RightAnswers rightAnswers = new RightAnswers();
        Question question = surveyService.findAllQuestions(id).get(number-1);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            Answer answer1 = question.getAnswers().get(i);
            if (answer.equals(String.valueOf(answer1.getNumber()))) {
                if (answer1.getCorrectness()) {
                    rightAnswers.addQuestion(question, true);
                } else {
                    rightAnswers.addQuestion(question, false);
                }
                rightAnswers.setResults(results);
                surveyService.saveResult(rightAnswers);
                break;
            }
        }
        model.addAttribute("surveyId", id);
        model.addAttribute("resultId", resultId);
        List<Answer> answers = survey.getQuestions().get(number-1).getAnswers();
        model.addAttribute("questions", survey.getQuestions().size());
        model.addAttribute("question", survey.getQuestions().get(number-1));
        model.addAttribute("answers", answers);
        if (survey.getQuestions().size() - 1 == number) {
            Timestamp ts = Timestamp.from(Instant.now());
            results.setEnd_time(ts);
            long time_difference = ts.getTime() - results.getStart().getTime();
            long hours_difference = TimeUnit.MILLISECONDS.toHours(time_difference) % 24;
            long minutes_difference = TimeUnit.MILLISECONDS.toMinutes(time_difference) % 60;
            long seconds_difference = TimeUnit.MILLISECONDS.toSeconds(time_difference) % 60;

            List<RightAnswers> rightAnswersList = surveyService.results(results.getId());
            int counter = 0;
            for (RightAnswers rightAnswersElement : rightAnswersList) {
                if(rightAnswersElement.getaBoolean())
                    counter++;
            }
            model.addAttribute("allAnswers", rightAnswersList.size());
            model.addAttribute("counter", counter);
            model.addAttribute("hours", hours_difference);
            model.addAttribute("minutes", minutes_difference);
            model.addAttribute("seconds", seconds_difference);
            surveyService.deleteResultsById(resultId);
            return "statistics";
        }
        return "passSurvey";
    }
}
