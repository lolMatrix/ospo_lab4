$("#auth").submit(function (event) {

    var name = $('input[name="name"]').val();
    var password = $('input[name="password"]').val();
    var json = {name: name, password: password};

    if ($('input[name="repass"]').length) {
        var repass = $('input[name="repass"]').val()
        if (repass !== password) {
            alert("Пароли не совподают");
            event.preventDefault();
            return;
        }

        $.ajax({
                url: '/api/reg',
                type: 'POST',
                data: JSON.stringify(json),
                contentType: 'application/json',
                dataType: 'json'

            }).fail(function(data) {
                if (!data.responseText.includes("success"))
                    alert(`Пользователь уже зарегистрирован`)
                else
                    alert(`Успешная авторизация для ${name}`)
            });

        event.preventDefault();
        return;
    }

    $.ajax({
        url: '/api/login',
        type: 'POST',
        data: JSON.stringify(json),
        contentType: 'application/json',
        dataType: 'json'

    }).fail(function(data) {
          if (data.responseText === "error login"){
            $("#pass").append('<p>Повторите пароль</p><input type="password" name="repass">')
          } else {
            if (data.responseText.includes("success auth for"))
                alert(`Успешная авторизация для ${name}`)
            else alert(`Произошли технические шоколадки ${data.responseText}`)
          }
        });

    event.preventDefault();
});