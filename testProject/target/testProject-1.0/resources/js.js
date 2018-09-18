var mass;
var sl = "\\\\";
var inputPath = '';

//Переход в выбранную папку
function openFolder(obj){
    var folder = obj.text();
    mass[0] = inputPath;
    mass.push(sl + folder);
    var output = '';
    
    for (var i = 0; i < mass.length; i++){
        output += mass[i];
    }
    stompClient.send("/app/user/" + output, {}, JSON.stringify({}));
    document.getElementById('path').value = output;
}

//Возврат в родительскую папку
function back() {
    if (mass.length > 1) {
        mass.pop();
        var directory = "";
        for (var i = 0; i < mass.length; i++) {
            directory += mass[i];
        }
        stompClient.send("/app/user/" + directory, {}, JSON.stringify({}));
        document.getElementById('path').value = directory;
    }
}

//Соединение с сервером
function connect() {
    var socket = new SockJS('/files');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/files', function (response) {
            //console.log(response);
            var list = JSON.parse(response.body);
            //console.log(list);
            showList(list);
        });
    });
}

//Разъединение с сервером
function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}

//Отправка директории на сервер
function send() {
    console.log("sending");
    inputPath = document.getElementById("path").value;
    stompClient.send("/app/user/" + inputPath, {}, JSON.stringify({}));
    mass = [];
}

//Вывод списка файлов и папок
function showList(list) {
    var html_folder = '';
    var html_file = '';
    for (var i in list) {
        if (list[i]['isDirectory'] == 1) {
            html_folder += '<li><div class="name folder_bg" onclick=openFolder($(this))>' + list[i]['name'] + '</div>'
                             + '<div class="date">' + list[i]['date'] + '</div>'
                             + '<div class="size">' + list[i]['size'] + '</div></li>';
        }
        else
            html_file += '<li><div class="name file_bg">' + list[i]['name'] + '</div>'
                           + '<div class="date">' + list[i]['date'] + '</div>'
                           + '<div class="size">' + list[i]['size'] + '</div></li>';
    }
    document.getElementById("folder").innerHTML = html_folder;
    document.getElementById("file").innerHTML = html_file;
}