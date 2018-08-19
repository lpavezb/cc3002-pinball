package gui;

/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

/**
 * Class for controlling a ball component
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 * @author Lukas Pavez
 * @see Component
 */
public class BallComponent extends Component {

    private PhysicsComponent physics;
    private int maxVelocity = 8;

    @Override
    public void onUpdate(double tpf) {
        limitVelocity();
    }

    private void limitVelocity() {
        if (Math.abs(physics.getLinearVelocity().getX()) < maxVelocity * 60) {
            physics.setLinearVelocity(Math.signum(physics.getLinearVelocity().getX()) * maxVelocity * 60,
                    physics.getLinearVelocity().getY());
        }

        if (Math.abs(physics.getLinearVelocity().getY()) > maxVelocity * 60 * 2) {
            physics.setLinearVelocity(physics.getLinearVelocity().getX(),
                    Math.signum(physics.getLinearVelocity().getY()) * maxVelocity * 60);
        }
    }
}
