# Electric RGB

Forge 1.20.1 mod that adds RGB lightbulbs and FE cables that are compatible with Create Crafts & Additions through the standard Forge Energy capability.

## Highlights
- RGB Lightbulb: stores FE, consumes power to stay lit (20 FE/t), and can be recolored in-place with any dye.
- Copper Cable: simple FE transporter (16k buffer, 400 FE/t transfer) that will accept and push FE to neighbors, including Create Crafts & Additions connectors.
- Custom creative tab to access all new blocks.

## Building
1. Install JDK 17.
2. (Optional) Run `gradle wrapper` to generate the wrapper if you prefer not to use a global Gradle install.
3. Build with `gradle build` or run a dev client/server with the ForgeGradle run configs.

Dependencies for Create are not required unless you want to compile against its API; the mod works through the FE capability alone. Uncomment the Create dependency stubs in `build.gradle` and set the versions you use if you need them.
