// Copyright 2011 Google Inc. All Rights Reserved.
// Author: sxp@google.com (Sameer Padala)
//
// This is an inefficient edge detector used for testing the fragment shader.
//
// It is slow because it uses dependent texture looksups. The better method
// involves precalculating the offsets that texture2D need insider the vertex
// shader so they can be prefetched for fragment shader.

precision mediump float;
varying vec2 v_texCoord;
uniform sampler2D y_texture;
uniform sampler2D uv_texture;

void main() {
  vec2 offsets[9];
  offsets[0] = vec2(-1.0, -1.0);
  offsets[1] = vec2(-1.0,  0.0);
  offsets[2] = vec2(-1.0,  1.0);
  offsets[3] = vec2( 0.0, -1.0);
  offsets[4] = vec2( 0.0,  0.0);
  offsets[5] = vec2( 0.0,  1.0);
  offsets[6] = vec2( 1.0, -1.0);
  offsets[7] = vec2( 1.0,  0.0);
  offsets[8] = vec2( 1.0,  1.0);

  vec4 filters[9];
  filters[0] = vec4(-1.0,  1.0, -1.0,  1.0);
  filters[1] = vec4(-2.0,  2.0,  0.0,  0.0);
  filters[2] = vec4(-1.0,  1.0,  1.0, -1.0);
  filters[3] = vec4( 0.0,  0.0, -2.0,  2.0);
  filters[4] = vec4( 0.0,  0.0,  0.0,  0.0);
  filters[5] = vec4( 0.0,  0.0,  2.0, -2.0);
  filters[6] = vec4( 1.0, -1.0, -1.0,  1.0);
  filters[7] = vec4( 2.0, -2.0,  0.0,  0.0);
  filters[8] = vec4( 1.0, -1.0,  1.0, -1.0);

  gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0);

  int i;
  for (i=0; i<9; ++i) {
    vec2 xy = v_texCoord;
    xy.x += (1.0/640.0)*float(offsets[i].x);
    xy.y += (1.0/480.0)*float(offsets[i].y);
    gl_FragColor += texture2D(y_texture, xy).r * filters[i];
  }

  gl_FragColor.r = abs(gl_FragColor.r);
  gl_FragColor.g = abs(gl_FragColor.b);
  gl_FragColor.b = (gl_FragColor.r + gl_FragColor.g > 1.0) ? 1.0 : 0.0;
  gl_FragColor.a = 1.0;
}
