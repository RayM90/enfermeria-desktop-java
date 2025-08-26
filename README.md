# 🏥 Enfermería Desktop Java

Aplicación de escritorio desarrollada en **Java** con **MySQL** para la gestión integral de una **enfermería universitaria**.  
Permite registrar pacientes, gestionar historial médico, controlar citas, y generar reportes administrativos. El proyecto está diseñado para ser escalable y colaborativo.

---

## 🚀 Tecnologías utilizadas
- 💻 **Java 24**  
- 🗄️ **MySQL 8.x**  
- 📦 **Maven** para gestión de dependencias  
- 🛠️ **IntelliJ IDEA 2024.3.5**  
- 🔗 **JDBC (Java Database Connectivity)**  
- 🌐 **Git & GitHub** para control de versiones  

---

## ⚙️ Funcionalidades implementadas
- 🔌 Configuración de conexión a MySQL mediante `ConexionBD` y archivo `db.properties`.  
- ✅ Prueba de conexión y manejo de errores en clase `Main`.  
- 🧾 Registro completo de pacientes:

| Campo              | Tipo       | Ejemplo                  |
|-------------------|------------|-------------------------|
| Documento          | String     | 12345678                |
| Nombres            | String     | Raymar                  |
| Apellidos          | String     | Martínez                |
| Fecha de nacimiento| Date       | 2025-05-21              |
| Sexo               | int        | 2 (Femenino)            |
| Tipo de sangre     | int        | 7 (O+)                  |
| Correo electrónico | String     | raymar_14@gmail.com     |
| Teléfono           | String     | 04121234567             |
| Dirección          | String     | Pendiente               |

- 📋 Listado, actualización y eliminación de pacientes.  
- 🔧 Estructura modular para integración de nuevos módulos: citas, historial médico, consultas y reportes.  

---

## 📂 Estructura del proyecto

Enfermería/
├── src/main/java/org/enfermeria/
│ ├── config/ # Configuración de la base de datos
│ ├── dao/ # Clases DAO para acceso a datos
│ ├── model/ # Modelos de datos (Paciente, TipoDocumento, Sexo, etc.)
│ └── service/ # Clase Main y lógica de interacción
├── Base_de_dato/ # Archivo de diseño de la base de datos (.mwb)
├── pom.xml # Configuración de Maven
└── README.md # Documentación del proyecto


---

## 💡 Próximos pasos
- 🏗️ Completar campos pendientes: dirección y validaciones de teléfono.  
- 🏥 Implementar módulos de consultas y historial médico.  
- 📊 Generar reportes administrativos y gráficos.  
- 💾 Exportar base de datos final en formato `.sql` para despliegue o colaboración.  
- 🎨 Mejorar interfaz de usuario (opcional: JavaFX o Swing más avanzado).  

---

## 📌 Notas importantes
- Actualmente se está trabajando en la integración completa de los módulos y validaciones.  
- El proyecto está versionado en GitHub para permitir colaboración y seguimiento de cambios.  
- Todos los colaboradores deben usar **MySQL Workbench** y mantener sincronizada la estructura de la base de datos.


