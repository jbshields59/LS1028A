SUMMARY = "Take photos and videos with your webcam, with fun graphical effects"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a17cb0a873d252440acfdf9b3d0e7fbf"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 2)

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base libcanberra gtk+3 clutter-1.0 clutter-gst-3.0 clutter-gtk-1.0 vala-native gnome-desktop3 libxml2-native gdk-pixbuf-native itstool-native"

PR = "r0"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase pkgconfig gobject-introspection vala features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

GNOME_COMPRESS_TYPE = "xz"
GNOME_MIRROR = "https://ftp.acc.umu.se/pub/GNOME/sources/"
SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive \
           file://0001-change-encoding-profile-hide-video-recording-timesta.patch \
           file://0002-don-t-build-help-folder-to-avoid-build-break.patch \
"

FILES_${PN} += "${datadir}/dbus-1 ${datadir}/metainfo"

SRC_URI[archive.md5sum] = "13f8326689df42b0aedda023705db803"
SRC_URI[archive.sha256sum] = "d1865600ac88012e136b1ec3db72a4634f57bc2035895277be792bb078627e73"

EXTRA_OEMESON += "-Dintrospection=false -Dgtk_doc=false -Dman=false"

do_install_append() {
    rm -f ${D}${datadir}/icons/hicolor/scalable/apps/org.gnome.Cheese.svg
    rm -f ${D}${datadir}/icons/hicolor/symbolic/apps/org.gnome.Cheese-symbolic.svg
}
