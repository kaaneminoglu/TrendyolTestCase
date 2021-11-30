Feature: Login feature

  @Test
  Scenario: UI002.SuccessfulLogin
    When Open login page
    And "rgm44198@boofx.com" and "Test1234" are entered.
    Then Check account user "Hesabım"

  @Test
  Scenario: UI003.UnsuccessfulLogin
    When Open login page
    And "rgm44198@boofx.com" and "Test12345" are entered.
    Then Check account user "Hesabım"

  @Test
  Scenario Outline: UI004.CheckPasswordWrong
    Given Open login page
    When "<username>" and "<password>" are entered.
    Then Error message is "<errorMessage>"
    Examples:
      | username             | password  | errorMessage                               |
      |                      |           | Lütfen geçerli bir e-posta adresi giriniz. |
      | rgm44198@            |           | Lütfen geçerli bir e-posta adresi giriniz. |
      | rgm44198ı@gmail.com  |           | Lütfen geçerli bir e-posta adresi giriniz. |
      | rgm44198ı@gmai@l.com |           | Lütfen geçerli bir e-posta adresi giriniz. |
      | rgm44198@gmail       |           | Lütfen şifrenizi giriniz.                  |
      | rgm44198@gmail.co    |           | Lütfen şifrenizi giriniz.                  |
      | rgm44198@gmail.co    | 1         | E-posta adresiniz ve/veya şifreniz hatalı. |
      | rgm44198@boofx.com   | Kaan12345 | E-posta adresiniz ve/veya şifreniz hatalı. |