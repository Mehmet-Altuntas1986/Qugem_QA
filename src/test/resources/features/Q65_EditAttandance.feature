Feature: Edit an Employees Attendance

  Background: Authorized User logged in Qugem
    #@PRECOND_QUGEM-26
    Given the user logged in as 'admin'

  Scenario: the admin can be able to edit attendance
    When the user navigates to 'attendance'
    And the user clicks to edit button of Employee '007'
    And  the user edit attendance for 13 day as 'N'
    Then verify attendance update




