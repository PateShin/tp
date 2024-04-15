---
layout: page
title: Ondre Tann's Project Portfolio Page
---

### Project: PayBack

PayBack is comprehensive tool designed to streamline one's employee information management process.

Given below are my contributions to the project.

* **New Feature**: Confirmation message for `/delete` command
  * What it does: allows the user confirm/abort the deletion of employees from database.
  * Justification: This feature improves the product significantly because a user can make mistakes in `/delete` commands and the app should provide a confirmation message showing the details of employee to be deleted, before the user confirms/aborts the process.
  * Highlights: This enhancement requires modification to the main Logic class. It requires an additional parser to parse `Y` or `N` confirmation commands input by the user as well as additional parser state that determines whether it is parsing the usual feature commands or confirmation commands.

* **New Feature**: `/view` command
  * What it does:  allows the user to view transactions of an existing employee.
  * Justification: This feature is pivotal in this app which has a main goal of managing details and transactions of employees. `/transaction` command will have little to no use in this app if users cannot view the transactions of an existing employee.
  * Highlights: Similar to how employees are displayed in a `UniquePersonsList`, transactions will be displayed in a observable `UniqueTransactionsList` that is filtered based on `employeeId`. This filtered list will be shown to the user through the transaction list panel, which is an added UI feature to cater to the needs of our app.

* **New Feature**: `/tag` command
  * What it does: allows the user to tag an existing employee.
  * Justification: This feature conveniently allows the user to tag employees with any tags. This eases the process of filtering through the employees via the `/find` command. Contact management process is thus more efficient.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=ondretann&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Altered GUI layout to introduce transactions panel list (Pull requests [\#73](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/73))
  * Enhanced delete feature for delete cascading for transactions with `employeeId` = `Id` (Pull requests [\#77](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/77))
  * Added test cases for existing and new features (Pull requests [\#44](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/44), Pull requests [\#67](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/67), Pull requests [\#75](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/75))
* **Documentation**:
  * User Guide:
    * Modified documentation for the feature `/delete` [\#183](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/183)
    * Added documentation for the feature `/tag` [\#72](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/44)
    * Added documentation for the feature `/view` [\#86](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/86)
    * Modify formatting for UG website [\#196](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/196)[\#198](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/198)
    
  * Developer Guide:
    * Added implementation details of the `/delete` feature
    * Added and modified UML diagrams as our project evolved
    * Added use case for `/delete` feature
    * Added use case for `/view` feature
    * Added use case for `/tag` feature
    * 
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#39](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/39), [\#69](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/69), [\#74](https://github.com/AY2324S2-CS2103T-T12-4/tp/pull/74)


* _{you can add/remove categories in the list above}_
