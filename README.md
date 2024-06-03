# PaymentMethods.

En este pequeño proyecto se generan medios de pago. Pagos con tarjeta de crédito, pagos con qr y con nfc.
Antes un usuario debe registrarse en la app. Una vez hecho, se accede a la pantalla principal donde se verán datos de la persona registrada, tarjetas registradas y acceso a diferentes funciones.

## LogIn:

En la pantalla de Login tendremos los campos de Email y Contraseña para ingresar a la app con nuestros datos existentes.
Si el usuario no está registrado accederá a la pantalla de registro tipeando el texto "Registrarse"

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_1.png?alt=media&token=6e0af587-f077-43ba-afe0-7ee63bd3715a)

En este apartado se utiliza el logueo y registro con la Api de FirebaseAuth. 

Desde el FragmentLogin se llama al método "signInWithEmailAndPassword(email, password)", pasando antes por algunas inyecciónes de dependencias. 
Obteniendo el resultado y guardandolo en un LiveData se llama luego desde el fragmento con un observer y se valida.
Si es exitoso el login se accederá a la pantalla de tarjetas asociadas. si no lo es, deberá registrarse como usuario.


![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_2.png?alt=media&token=f43600f6-2c92-4498-bbd4-fdd253c117de)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_3.png?alt=media&token=62f05b9d-2a98-4bdf-8411-d5d82714c820)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_4.png?alt=media&token=322c194f-b504-40fe-a928-d50cf9c141df)

## Account:

En este apartado gráfico están los campos de registro, tales como el nombre completo (el cual será almacenado en la memoria interna para luego cotejar con las tarjetas que se quiera asociar a la cuenta),
tambien se deberá completar el registro con una dirección de email y una contraseña. Ambos campos deben ser validos, de lo contrario no podrá registrarse como usuario.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_5.png?alt=media&token=5daa764a-6d43-4a7a-a464-4bb65d6bc147)

Para registrar a una persona como usuario se valida (al igual que en Login) que la dirección de email y la contraseña sean validos. una vez realizado con exito, el nuevo usuario
ingresará a la pantalla de tarjetas. si se mata la aplicación y se vuelve a entrar no será necesario loguearse nuevamente, ya que el registro quedará almacenado en la memoria interna de la app

Los métodos para registrar una persona son los siguientes:

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_6.png?alt=media&token=c99743ff-a347-4580-ba93-764ac9f628c1)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_7.png?alt=media&token=32e31b28-5ad4-4906-8ef9-fc3193e9d113)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_8.png?alt=media&token=6b1ce44c-cd99-4583-ab85-7083f05f33c5)

En ambos casos; Login y Account se utilizaron diferentes clases que, tienen en común el uso de Inyección de dependencias. 
Para almacenar la información en la memória de la app, por cuestiones de tiempo se utilizó "SharedPrefences". La idea inicial fué utilizar room pero no me dió el tiempo. 

Desde el fragmentAccount se utilizó el método de dataStore para almacenar el nombre de la persona que se está registrando.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_9.png?alt=media&token=449ac4c0-acf4-4cf2-89cd-81bc62716bc0)

Luego desde la clase "LocalDataStore" se maneja la información requerida por los diferentes fragments

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_10.png?alt=media&token=8a391872-249f-41bd-820f-bf074f58a5b4)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_11.png?alt=media&token=eace4451-3695-4169-8d5a-b1c1719a8ea1)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_12.png?alt=media&token=be715b61-d1a7-474b-ad6c-803ebda576d2)

## Pantalla de Resumen Y Agregar Tarjeta:

Esta pantalla muestra las tarjetas asociadas (si es que las hay) y el acceso a agregar una tarjeta o generar un Qr.
Debería figurar el saldo de la tarjeta inicial dentro de la imagen de la tarjeta roja.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_13.png?alt=media&token=5fd2ac1c-fd9b-4afb-af3a-d909e4d434da)

en el espacio entre la imagen y el bottomBar se irán incluyendo las tarjetas que se asocien a la cuenta.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_14.png?alt=media&token=35309a59-a450-4d88-a6b9-dc211c009152)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_15.png?alt=media&token=c0cfa5a6-cbca-484a-b741-46cdb0adf18d)

Las tarjetas se agregan con datasTore de Sharepreferences como se vé mas arriba. una tarjeta nueva se genera desde el Fragment AddNewCardFragment.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_16.png?alt=media&token=51d71b76-4050-4d1e-b42c-c4b6b6dbee81)

## QR:

Desde el bottomBar se ingresa a la pantalla de Qr. En la cual con el nombre del usuario se podrá crear una imagen QR. esta información se obtuvo de una api diferente a la
habilitada en el challenge. ya que hice uso de los llamados permitidos y ya no me funcionó. esta es la que usé: "https://api.qrserver.com/v1/create-qr-code/"
 estos son los métodos de llamado a la Api:

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_17.png?alt=media&token=21a2be7e-95d8-47b6-8f3c-9b3be9254afb)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_18.png?alt=media&token=cde609f3-95e4-4e45-986e-7e31544d3777)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_19.png?alt=media&token=9a0597f9-46e7-4d2e-82b5-605d550b0479)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_20.png?alt=media&token=4888d9cf-94a4-4f7e-8950-ec958c4ddbad)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_21.png?alt=media&token=9f297d00-8816-433b-95b2-124a9bb1ce0c)

Solo incluí en imagenes los métodos que me parecieron escenciales de mostrar. pero hay algunos mas, como UseCase, repository, etc.
De esta respuesta se obtiene: 

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_22.png?alt=media&token=9c6dea2b-0e76-4152-b44a-6dd857f1567c)

#NFC:
Desde la clase CardFragment, si se tipea sobre algún item de la lista de tarjetas asociadas se accederá a una pantalla donde se verán los datos de la tarjeta y un botón que abrirá la opción de pago con NFC. esta última función no se ha implementado. la pantalla es solo de modo informativa y simula lo que sería un pago con el sistema Nfc.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_23.png?alt=media&token=e8faea02-b8b7-434c-93e5-cbfc8c19e426)

Los datos de que figuran en la imagen de la tarjeta se obtuvieron de dataStore filtados por el Número de tarjeta. El cual es enviado por el adapter de CardFragment a 
traves del navigator destination.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_24.png?alt=media&token=f510c486-967c-4591-932f-bf5bc637f3f8)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_25.png?alt=media&token=569d65db-bb64-4f0f-9a8b-e2eddc3bbfa6)

##Nota:
Me quedaron varias cosas afuera. cosas que quisiera haber agregado. Como por ejemplo una rueda de carga entre las pantallas de login, account y al agregar una tarjeta.
una imagen o label que indique que no hay tarjetas asociadas en la pantalla de tarjetas. y un poco más de proligidad.
Espero haber estado a la altura. Desde ya muchas gracias por la oportunidad.  

SALUDOS

TORRES HERNAN OSVALDO








