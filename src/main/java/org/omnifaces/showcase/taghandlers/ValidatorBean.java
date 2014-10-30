package org.omnifaces.showcase.taghandlers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.showcase.model.ExampleEntity;

@ManagedBean
@ViewScoped
public class ValidatorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Question> questions;
	private List<ExampleEntity> entities;

	@PostConstruct
	public void init() {
		questions = Arrays.asList(new Question(1, 9), new Question(5, 10), new Question(3, 7));
		entities = Arrays.asList(new ExampleEntity(), new ExampleEntity(), new ExampleEntity());
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<ExampleEntity> getEntities() {
		return entities;
	}

	public static class Question {

		private Integer answer;
		private Integer minimum;
		private Integer maximum;

		public Question(Integer minimum, Integer maximum) {
			this.minimum = minimum;
			this.maximum = maximum;
		}

		public Integer getAnswer() {
			return answer;
		}

		public void setAnswer(Integer answer) {
			this.answer = answer;
		}

		public Integer getMinimum() {
			return minimum;
		}

		public void setMinimum(Integer minimum) {
			this.minimum = minimum;
		}

		public Integer getMaximum() {
			return maximum;
		}

		public void setMaximum(Integer maximum) {
			this.maximum = maximum;
		}

	}

}