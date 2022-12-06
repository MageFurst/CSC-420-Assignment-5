# CSC-420-Assignment-5
This homework is an upgrade to both HW2 and HW4. It requires you to use the same data model for multiple components; allow the user complex drag-and-drop behavior; and optimally draw multiple updates per second on a canvas (no flickering).

Your layout consists of the following elements: a JList containing the names of various contries for which you have flags, similar to HW4; and a canvas (I highly recommend a JPanel), where you will paint. There are no textboxes, buttons, etc. The canvas has four sliders around it, as explained below. Make sure all components are laid out well, the sliders match the canvas size, etc.

The canvas in your application should be surrounded by four sliders (one above, one below, one to the left, one to the right). Whenever the user moves a slider, the opposite slider should move as well, thus defining a line perpendicular to the axis of slider motion. Make sure you use two sliders attached to the same model, rather than listeners to enable the synchronized behavior.

If no flag is displayed yet on the canvas, you should clearly show where it will eventually be drawn by painting a crosshairs on the canvas. The crosshairs should move as the user moves the sliders.

The user is able to drag a country name from the JList onto the canvas (any position on the canvas). When that happens, you should display the flag of the respective country at the position of the crosshairs (the crosshairs should disappear). Use sensible resizing of the flags. Further motion of the sliders moves the flag around without flicker.

Hints: you want to look up how to do drag and drop of complex custom objects (note that JPanel extends JComponent, and is not a top-level component); how to do flickerless animation; and how to connect multiple views to the same model (for the two pairs of sliders).

Submit your sources and resources to the Blackboard dropbox before the deadline.

I'll want you to demonstrate how your homework works after you submit it, too.

All Swing homeworks are individual. Group work is not permitted.
