<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Trendyol_online.png/800px-Trendyol_online.png"/>

# Trendyol Test Case
## Overview

Includes trendyol test scenarios. It outputs the screenshot and the csv file.

```cucumber
  Scenario: UI001.HomepageList
    When Check boutique link
    And Scroll down to footer
    And Check boutique image duration and status code
```

```cucumber
  Scenario: UI002.SuccessfulLogin
    When Open login page
    And "email" and "Test1234" are entered.
    Then Check account user "Hesabım"
```

```cucumber
  Scenario: UI003.UnsuccessfulLogin
    When Open login page
    And "email" and "Test12345" are entered.
    Then Check account user "Hesabım"
```

```cucumber
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
      | rgm44198@boofx.com   | Test12345 | E-posta adresiniz ve/veya şifreniz hatalı. |
```


License
--------


    Copyright 2021 kaaneminoglu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
