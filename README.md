# Physics Sandbox

## Description

A simple sandbox to implement physics integrators and show their results graphically.

## Usage

### Prerequisites

- [VS Code](https://code.visualstudio.com)
  - with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) by *Microsoft*
- [OpenJDK](https://adoptium.net)

### Execute the Program

1. Clone/download this repository and open it in *VS Code*.
2. Open one of the `*.java` files. (if there are any errors, open `Main.java` once to let *VS Code* know that it is the entry point of the program)
3. Wait for any progress bars in the bottom right corner to complete.
4. Hit *F5* or the small arrow in to top right corner of the editor window.

### Add Objects

Create a new class in `Objects.java` which extends `Obj` and override the `update(double deltaTime)` method:

```java
class FallingObject extends Obj {
    @Override
    void update(double deltaTime) {
        // TODO
    }
}
```

Now add your object to the `Simulation.objects` list in `Simulation.java`:

```java
static {
    // Add objects
    objects.add(new FallingObject()); // FallingObject is the name of the new object
}
```

This is enough to show a black circle with radius `1` at `0,0` (bottom-left corner).
To make it do something manipulate the `pos_x` and `pos_y` variables with the help of the `vel_x`, `vel_y`, `acc_x` and `acc_y` variables.
The color, radius and mass of the object can be changed with the `color`, `radius` and `mass` variables, respectively.

Example (*Objects.java*):

```java
import java.awt.Color;

class FallingObject extends Obj {
    FallingObject() {
        pos_x = 10;
        pos_y = 60;
        color = Color.RED;
    }

    @Override
    void update(double deltaTime) {
        acc_x = 0;
        acc_y = -9.81;

        vel_x = vel_x + acc_x * deltaTime;
        vel_y = vel_y + acc_y * deltaTime;

        pos_x = pos_x + vel_x * deltaTime;
        pos_y = pos_y + vel_y * deltaTime;

        // Bounce when the object hits the ground.
        if (pos_y - radius <= 0 && vel_y < 0) {
            vel_y = -vel_y;
        }
    }
}
```

### Change Settings

You can change the size and scale of the world and other values like the target frame count per second or physics update count per frame in `Simulation.java`.

## License

Copyright (c) 2023 Julian Hofmann

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
