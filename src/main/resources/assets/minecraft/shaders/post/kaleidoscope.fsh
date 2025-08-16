#version 150

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

uniform sampler2D InSampler;
uniform sampler2D TextureSampler;

in vec2 texCoord;
in mat4 ProjMat2;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform KaledoscopeConfig {
    float Segments;
    vec2 ImagePortion;
};

out vec4 fragColor;

const float PI = 3.1415926535897932384626433832795;
const float TAU = 6.283185307179586476925286766559;

void main() {
		vec2 shiftXY = texCoord.xy - 0.5;
		float radius = sqrt(dot(shiftXY, shiftXY));
		float angle = atan(shiftXY.y, shiftXY.x);
		
    float segmentAngle = TAU / Segments;

		angle -= segmentAngle * floor(angle / segmentAngle);

		angle = min(angle, segmentAngle - angle);

		vec2 xy = vec2(cos(angle), sin(angle)) * radius + 0.5;
		
		xy = max(min(xy, 1.0 - xy), -xy);
		vec3 camera = ModelViewMat[0].xyz;
		float yaw = atan(camera.x, camera.z);
		float pitch = asin(camera.y);
    vec4 OutTexel = texture(TextureSampler, ProjMat2[2].xy);

// Texture should be a square, taking a chunk from the texture sampler, in the image portion size...
// This should then be shifted around, based on the angle the player is looking.
// Then, the kaleidoscope effect should occur.
    
//    vec4 OutTexel = texture(TextureSampler, xy);

//		if (texCoord.y < 1.0 && texCoord.x < 1.0 && texCoord.y > 0.9 && texCoord.x > 0.9) OutTexel.rgb = vec3(0.0, 0.0, 1.0);
//		if (texCoord.y < 0.1 && texCoord.x < 0.2) OutTexel.rgb = vec3(1.0, 0.0, 1.0);
		if (texCoord.y < 0.1 && texCoord.x < 0.1) OutTexel.rgb = vec3(ModelViewMat[1].x, ModelViewMat[1].y, 0.0);
    fragColor = vec4(OutTexel.rgb, 1.0);
}
