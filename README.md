






ESTRUCTURA DE CARPETAS Y ARCHIVOS
---------------------------------------


Android-SDD
│
├── agents
│   ├── android-architect.md
│   ├── android-developer.md
│   ├── compose-expert.md
│   ├── product-owner.md
│   └── reviewer.md
│
├── assets
│
├── behavior
│   ├── apply.md
│   ├── archive.md
│   ├── design.md
│   ├── proposal.md
│   ├── tasks.md
│   └── verify.md
│
├── changes
│
├── docs
│   ├── android-standards.md
│   ├── architecture.md
│   ├── base-standards.md
│   └── development-guide.md
│
├── knowledge
│   ├── business-rules.md
│   ├── coding-guidelines.md
│   ├── domain-model.md
│   ├── navigation.md
│   ├── project-context.md
│   ├── testing-guidelines.md
│   └── ui-guidelines.md
│
├── learning
├── projects
├── scripts
├── templates
│   ├── design
│   ├── proposal
│   └── tasks
│
├── .github
├── .vscode
├── .gitignore
├── codex.md
└── README.md





MAPA DE ANDROID-SDD
--------------------------------------------------------

Así es como lo veo ahora:

Android-SDD
│
├── docs/              ← Reglas globales
│
├── knowledge/         ← Conocimiento del proyecto
│
├── agents/            ← Roles especializados
│
├── behavior/          ← Workflow SDD
│
├── templates/         ← Plantillas
│
├── changes/           ← Features
│
└── projects/          ← Apps Android





Ahora vamos a definir cada documento
---------------------------------------------------------------------------



----------------------------
📁 docs/
----------------------------

No habla del proyecto.

Habla de cómo desarrollamos.
------------------------------

base-standards.md

👉 Constitución del proyecto.

android-standards.md

👉 Reglas de Kotlin, Compose, Room, Hilt, Coroutines...

architecture.md

👉 Arquitectura oficial.

Por ejemplo:

UI

↓

ViewModel

↓

UseCase

↓

Repository

↓

Datasource
development-guide.md

👉 Cómo ejecutar el proyecto.

Cómo hacer tests.

Cómo crear una nueva feature.



📁 knowledge/

Aquí sí vive el proyecto.

project-context.md

¿Qué aplicación estamos construyendo?

business-rules.md

Las reglas de negocio.

Ejemplo:

Una tarea solo puede tener una prioridad.
domain-model.md

Las entidades.

Task

Category

Reminder

User
navigation.md

Pantallas.

Home

↓

Task Detail

↓

Settings
ui-guidelines.md

Material 3.

Tema.

Colores.

Espaciados.

coding-guidelines.md

Convenciones Kotlin.

testing-guidelines.md

Cómo hacemos tests.




📁 agents/

Aquí no habrá prompts gigantes.

Solo especialización.

android-architect

Piensa arquitectura.

android-developer

Implementa.

compose-expert

Solo UI.

reviewer

Busca errores.

product-owner

Refina propuestas.

📁 behavior/

Aquí estará OpenSpec adaptado.

proposal

↓

design

↓

tasks

↓

apply

↓

verify

↓

archive
📁 templates/

Plantillas reutilizables.

📁 changes/

Aquí vivirá el trabajo diario.

Ejemplo:

changes/

add-dark-mode/

    proposal.md

    design.md

    tasks.md








------------------------------------------------------------------------------------------------------------

COMO TRABAJAR CON CHATGPT Y CON CODEX 
-------------------------------------------------------------------------------------------------------------


Prompt para ChatGPT :

ahora ya en codex le he dicho donde esta Android-SDD para que acceda .ahora pasame prompt para que le pase a codex con lo que queremos que haga