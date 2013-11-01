// Copyright 2011 Google Inc. All Rights Reserved.
// Author: sxp@google.com (Sameer Padala)
//
// Passes the vertex and texture data through to the fragment shader. Performs
// a standard Perspective-View matrix transformation on the vertex data

attribute vec4 VERTEX;
uniform mat4 PVMATRIX;

attribute vec2 TEXUV;
varying vec2 v_texCoord;

void main() {
  gl_Position = PVMATRIX * VERTEX;
  v_texCoord = TEXUV;
}
