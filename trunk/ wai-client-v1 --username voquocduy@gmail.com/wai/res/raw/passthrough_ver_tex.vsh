// Copyright 2011 Google Inc. All Rights Reserved.
// Author: sxp@google.com (Sameer Padala)
//
// Passes the vertex and texture data through to the fragment shader without
// any calculations.

attribute vec4 VERTEX;
attribute vec2 TEXUV;
varying vec2 v_texCoord;

void main() {
  gl_Position = VERTEX;
  v_texCoord = TEXUV;
}
