---
layout: page
title: Shin Dong Yoon's Project Portfolio Page
---

### Project: PayBack

PayBack is comprehensive tool designed to streamline one's employee information management process.

Given below are my contributions to the project.

* **New Feature**: `/find` command
    * What it does: Enables users to search for contacts in the system based on criteria like name, phone number, email, ID, year joined, or tags. This feature supports case-insensitive and partial matches (except Name, Year and ID), enhancing the search capability.
    * Justification: This feature is essential as it allows users to efficiently locate personal details of many employees, thereby improving the application's usability and user experience. It meets the need for a quick and flexible search mechanism in managing extensive employee information.
    * Highlights: This improvement implemented a modular and scalable method for search capabilities. It involved creating new predicate classes tailored to individual search criteria and integrating them with the current application framework. The development of this feature presented considerable challenges, including the necessity to maintain compatibility with diverse input formats and the pre-existing command framework.
      <br><br>
* **New Feature**: `/list` command
    * What it does: Displays all employees in PayBack , clearing any active filters.
    * Justification: This command enhances usability by providing an easy way for users to see all entries, especially useful after narrowing searches or applying filters.
    * Highlights: The implementation was straightforward but crucial for navigating the employee efficiently. It serves as a reset to view all contacts, a fundamental feature for managing large sets of data.
<br><br>
* **New Feature**: `AutoComplete CommandBox`
    * What it does: Suggests command completions to the user as they type in the command text field. It dynamically filters available commands based on the user's input prefix, providing real-time suggestions.
    * Justification: This feature significantly improves the user experience by speeding up command entry and reducing the likelihood of typographical errors. Users can easily discover available commands and their syntax, enhancing usability and productivity.
    * Highlights: The implementation involved integrating an auto-completion library and developing a system to match partial user inputs to commands. It also required updating the UI to display command suggestions and follow-up messages, enhancing user interaction with the application.
<br><br>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=pateshin&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
<br><br>
* **Project management**:
    * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub
<br><br>
* **Enhancements**:
    * Wrote additional tests for existing features to increase coverage (Pull requests [\#39](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/c60c271ccb591734c4c989a02f80c04f74bc3b38), [\#43](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/f8b4cf1659dd46ac67a350c5f42d78f4870b27b1), [\#169](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/a9e8832936fe975016812818b3dc5be9bd1e5b5d))
    * Edited the GUI color and design (Pull requests [\#74](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/6cc1a318c7b6c018416bc35200b6d63c2982bfa1))
<br><br>
* **Documentation**:
    * User Guide:
        * Added documentation for the features `/find` [\#39](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/c60c271ccb591734c4c989a02f80c04f74bc3b38), [\#169](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/a9e8832936fe975016812818b3dc5be9bd1e5b5d#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Added documentation for the features `AutoComplete`: [\#80](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/de3e8c7e967fd8a38998ea13680e9551403ee31b), [\#172](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/f0a551c72503591139fb0e814914d1a1f55b0d18)
    * Developer Guide:
      * Added UserStories and MSS: [\#23](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/4c4da46d1a505c885ab11f00740555310d8354f0)
      * Added implementation details of the `/find` feature.  
<br>
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#38](https://github.com/AY2324S2-CS2103T-T12-4/tp/commit/f94a995d7098baa5e658899883ececc89521748b) 

