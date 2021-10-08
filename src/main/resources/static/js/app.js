var controlador = (function () {

    var validarEdad = function (fecha) {
        var hoy = new Date();
        var cumpleanos = new Date(fecha);
        var edad = hoy.getFullYear() - cumpleanos.getFullYear();
        var m = hoy.getMonth() - cumpleanos.getMonth();
        if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
            edad--;
        }
        if (edad >= 18) {
            return true;
        } else {
            return false;
        }
    };

    function validarEmail(valor) {
        return /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(valor);
    };

    var registrar = function () {
        document.getElementById("error").innerText = "";
        var data = {};
        data.name = document.getElementById("nombre").value;
        data.email = document.getElementById("correo").value;
        data.date = document.getElementById("fecha").value;
        data.phone = document.getElementById("telefono").value;
        data.password = document.getElementById("password").value;
        if (validarEmail(data.email)) {
            if (validarEdad(data.date)) {
                if (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/i.test(data.password)) {
                    fetch("http://localhost:8080/iloveauction/registrar/usuario", {

                            method: "POST",
                            headers: {
                                "Content-type": "application/json"
                            },
                            body: JSON.stringify(data)
                        })
                            .then(response => location.href = "PaginaPrincipal.html")
                            .catch(err => {
                                console.log(err);
                            });
                }
                else{
                    document.getElementById("error").innerText = "Contraseña invalida.";
                }
            } else {
                document.getElementById("error").innerText = "Tienes que ser mayor de edad.";
            }
        } else {
            document.getElementById("error").innerText = "Correo invalido.";
        }
    };

    var crearEvento = function () {

        var nombre = document.getElementById("nombre").value;
        var descripcion = document.getElementById("descripcion").value;
        var fechaInicio = document.getElementById("fechaInicio").value;
        var fechaFin = document.getElementById("fechaFin").value;
        var ofertaInicial = document.getElementById("ofertaInicial").value;
        var idUsuario = UserModule.getIdUsuario();

        var data = {};
        data.name = nombre;
        data.description = descripcion;
        data.startDate = fechaInicio;
        data.endDate = fechaFin;
        data.initialOffer = ofertaInicial;
        data.usuario = idUsuario;

        fetch("http://localhost:8080/iloveauction/crear/subasta", {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => location.href="./PaginaPrincipal.html")
            .catch(err => {
                console.log(err);
            });

    };

    var init = function () { 
        
        fetch("http://localhost:8080/iloveauction/consultar/subastas")
            .then(response => response.json())
            .then(json => verEventos(json))
            .catch(err => {
                console.log(err);
            });
        
    };

    var verEventos = function (json) {

        var name;
        var description;
        var id;

        for (var i in json) {
            id = json[i].id;
            name = json[i].name;
            description = json[i].description.substring(0,20)+"...";


            var s = "<div class='col-lg-3 col-md-6 mb-4 mb-lg-0'  >" +
                "<div class='card rounded shadow-sm border-0' style='margin-bottom: 40px;'>" +
                "<div class='card-body p-4' >" +
                "<img src='css/sub.png' alt='' class='img-fluid d-block mx-auto mb-3'>" +
                "<h5 class='card-title'>" + name + "</h5>" +
                "<p class='small text-muted font-italic'>" + description + "</p>" +
                "<input onclick=\"window.location='./subasta.html?value=" + id + "';\"  type='button' value='ingresar' style='margin-left: 80px; margin-right: auto;'>" +
                "</div>" +
                "</div>" +
                "</div>";

            $("#divEvents").append(s);
        }

    };

    var password = function (json, contrasena) {
        if (json.password == contrasena) {
            document.cookie = "usuarioActual="+encodeURIComponent(JSON.stringify(json));
            location.href = "PaginaPrincipal.html";
        } else {
            document.getElementById('pasw').innerText = "Contraseña incorrecta.";
        }
    };

    var ingresar = function () {
        document.getElementById('pasw').innerText= "";
        var usuario = document.getElementById("usuario").value;
        var contrasena = document.getElementById("contrasena").value;
        if (validarEmail(usuario)) {

            fetch("http://localhost:8080/iloveauction/usuario/" + usuario,{method: 'GET',
                mode: 'cors',
                credentials: 'include'})
                .then(response => response.json())
                .then(json => password(json, contrasena))
                .catch(err => {
                    error();
                    console.log(err);
                });
        } else {
            document.getElementById("pasw").innerText = "Correo no valido.";
        }
    };

    var error = function () {
        document.getElementById("pasw").innerText = "El usuario no existe.";
    };
    
    var cerrarSesion = function(){
        document.cookie = "usuarioActual=; max-age=0";
        location.href = "index.html";
    };

    return {
        registrar: registrar,
        crearEvento: crearEvento,
        init: init,
        ingresar: ingresar,
        cerrarSesion: cerrarSesion
    };

})();


