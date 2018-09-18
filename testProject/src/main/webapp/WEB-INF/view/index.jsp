<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Java-web</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="resources/sockjs-0.3.4.js"></script>
    <script src="resources/stomp.js"></script>
    <script src="resources/js.js"></script>
    <link href="resources/style.css" rel="stylesheet" media="all">
</head>
<body>
<script >$(document).ready(function(){connect()});</script>
    <div class="header">
        <div class="center">
            <div class="task_name">Просмотр информации внутри директории</div>
            <span>Путь:</span>
            <input id="path" placeholder="Пример директории: C:\\Папка1\\Папка2...">
            <button onclick="send()">Перейти</button>
            <button onclick="back()">Назад</button>
            <button onclick="disconnect()" class="right">Выход</button>
        </div>
    </div>
    <div class="elements">
        <div class="title">
            <div class="name">Наименование</div>
            <div class="date">Дата создания</div>
            <div class="size">Размер</div>
        </div>
        <ul id="root">
            <div id="folder"></div>
            <div id="file"></div>
        </ul>
    </div>
</body>
</html>
