Estructura 
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/healthsensoravance/
│   │   │   ├── components/         # Componentes reutilizables 
│   │   │   ├── screens/            # Pantallas principales 
│   │   │   ├── ui/theme/           # Colores, tipografía y estilos
│   │   │   ├── viewmodels/         # Lógica y manejo de estado
│   │   │   ├── Routes.kt           # Rutas de navegación
│   │   │   ├── HealthSensorApp.kt  # Punto de entrada principal (NavHost)
│   │   │   └── MainActivity.kt     # Activity raíz
│   │   └── res/                    # Recursos 
│   └── AndroidManifest.xml
├── build.gradle.kts                # Configuración del módulo
└── settings.gradle.kts             # Configuración raíz del proyecto
