# PaymentMethods.

En este pequeño proyecto se generan medios de pago. Pagos con tarjeta de crédito, pagos con qr y con nfc.
Antes un usuario debe registrarse en la app. Una vez hecho, se accede a la pantalla principal donde se veran datos de la persona registrada, tarjetas registradas y acceso a diferentes funciones.

## LogIn:

En la pantalla de Login tendremos los campos de Email y Contraseña para ingresar a la app con nuestros datos existentes.
Si el usuario no está registrado accederá a la pantalla de registro con tipeando el texto "Registrarse"

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_1.png?alt=media&token=6e0af587-f077-43ba-afe0-7ee63bd3715a)

En este apartado se utiliza el logueo y registro con la Api de FirebaseAuth. 

Desde el FragmentLogin se llama al método "signInWithEmailAndPassword(email, password)", pasando antes por algunas inyecciónes de dependencias. 
Obteniendo el resultado y guardandolo en un LiveData se llama luego desde el fragmento con un observer y se valida.
Si es exitoso el login se accederá a la pantalla de tarjetas asociadas. si no lo es, deberá registrarse como usuario,

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_2.png?alt=media&token=f43600f6-2c92-4498-bbd4-fdd253c117de)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_3.png?alt=media&token=62f05b9d-2a98-4bdf-8411-d5d82714c820)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_4.png?alt=media&token=322c194f-b504-40fe-a928-d50cf9c141df)

## Account:

En este apartado gráfico están los campos de registro, tales como el nombre completo (el cual será almacenado en la memoria interna para luego cotejar con las tarjetas que se quiera asociar a la cuenta),
tambien se deberá completar el registro con una dirección de email y una contraseña, ambos campos deben ser validos, de lo contrario no podrá registrarse como usuario.

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_5.png?alt=media&token=5daa764a-6d43-4a7a-a464-4bb65d6bc147)

Para registrar a una persona como usuario se valida (al igual que en Login) que la dirección de email y la contraseña sean validos. una vez realizado con exito, el nuevo usuario
ingresará a la pantalla de tarjetas. si se mata la aplicación y se vuelve a entrar no será necesario loguearse nuevamente, ya que el registro quedará almacenado en la memoria interna de la app

Los métodos para registrar una persona son los siguientes:
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_6.png?alt=media&token=c99743ff-a347-4580-ba93-764ac9f628c1)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_7.png?alt=media&token=32e31b28-5ad4-4906-8ef9-fc3193e9d113)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_8.png?alt=media&token=6b1ce44c-cd99-4583-ab85-7083f05f33c5)

En ambos casos; Login y Account se utilizaron diferentes clases, tienen en común el uso de Inyección de dependencias. 
Para almacenar la información en la memória de la app, por cuestiones de tiempo se utilizó "SharedPrefences". La idea inicial fué utilizar room pero no me dió el tiempo. 

Desde el fragmentAccount se utilizó el método de dataStore para almacenar el nombre de la persona que se está registrando.
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_9.png?alt=media&token=449ac4c0-acf4-4cf2-89cd-81bc62716bc0)

Luego desde la clase "LocalDataStore" se maneja la información requerida por los diferentes fragments

![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_10.png?alt=media&token=8a391872-249f-41bd-820f-bf074f58a5b4)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_11.png?alt=media&token=eace4451-3695-4169-8d5a-b1c1719a8ea1)
![](https://firebasestorage.googleapis.com/v0/b/redsocialtrabajos.appspot.com/o/images%2FScreenshot_12.png?alt=media&token=be715b61-d1a7-474b-ad6c-803ebda576d2)
![]()
![]()
![]()
![]()






