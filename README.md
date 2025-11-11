## Explicación del formulario de inicio de sesión

1. **Contenedor principal (`auth-container`)**  
   Envuelve todo el contenido del formulario de autenticación  
   Sirve para centrar o dar estilo general a la vista de inicio de sesión

2. **Tarjeta de autenticación (`auth-card`)**  
   Contiene el título y el formulario  
   Le da un estilo visual de “tarjeta” al formulario

3. **Título `<h2>`**  
   Muestra el texto “Iniciar Sesión” centrado en la parte superior del formulario

4. **Formulario `<form>`**  
   Usa Angular con `(ngSubmit)="login()"` para ejecutar la función `login()` cuando el usuario envía el formulario  
   Tiene la referencia local `#loginForm="ngForm"` para controlar su estado y validaciones  
   Aplica la clase `auth-form` para estilos personalizados
