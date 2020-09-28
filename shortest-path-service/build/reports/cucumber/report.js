$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("interstellar.feature");
formatter.feature({
  "line": 1,
  "name": "Find shortest path",
  "description": "",
  "id": "find-shortest-path",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Shortest path between A and Z",
  "description": "",
  "id": "find-shortest-path;shortest-path-between-a-and-z",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "that A is the source planet and Z is the destination planet",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Path is not null",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "Shortest path is calculated and displayed",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});